const API_SEARCH_URL = '/api/search';

const MIN_CHARS = 3;
const MAX_RESULTS = 10;

const TAB_FILTERS = {
    all: null, //show everything
    shows: ['TV_SHOW', 'DOCUMENTARY','ANIME_SERIES', 'ANIME_OVA'],
    movies: ['MOVIE', 'ANIME_MOVIE'],
    manga: ['MANGA', 'MANHWA', 'MANHUA', 'DOUJINSHI', 'COMIC'],
    books: ['NOVEL', 'LIGHT_NOVEL'],
    games: ['GAME', 'VISUAL_NOVEL'],
    music: ['MUSIC', 'ANIME_MUSIC'],
    other: ['OTHER', 'UNKNOWN']
}

let activeTab = 'all';

let currentSearchResults = [];
let lastQuery = "";

const input = document.getElementById('title-input');
const search_dropdown = document.getElementById('search-dropdown')
const search_results = document.getElementById('search-results');
const add_form = document.getElementById('add-form');

// TODO: AbortController
export async function search() {

    const query = input.value;
    lastQuery = query;
    if (query.length < MIN_CHARS) {
        hideSearchResults();
        currentSearchResults = [];
        return;
    }
    const params = new URLSearchParams();
    //const testQuery = 'One';
    params.append('query', query);

    try {
        const response = await fetch(`${API_SEARCH_URL}?${params}`);
        const results = await response.json();

        if(query !== lastQuery)
            return;

        console.log("results json:");
        console.log(results);
        renderSearchResults(results)
    } catch (error)
    {
        console.error("Search failed: ", error);
    }
}

function renderSearchResults(results) {

    currentSearchResults = results;
    results.forEach((result, index) => result._index = index);
    onTabClick(activeTab);
    showSearchResults();
}

function renderGroups(results, limitPerGroup = null)
{
    const groupedResults = groupByMediaType(results);
    console.log(groupedResults);

    let html = '';

    Object.entries(groupedResults).forEach(([type, entries]) => {
        const entries_limited = limitPerGroup ? entries.slice(0, limitPerGroup) : entries;
        html += `
        <div class="search-column">
            <h3 class="search-column-header">${type}</h3>
            <ul class="search-column-list">
                ${entries_limited.map(entry => listEntry(entry)).join('')}
            </ul>
        </div>
        `
    });

    search_results.innerHTML = html || '<p class="no-results">No results in this category</p>';
}

export function fillForm(e)
{
    e.preventDefault();
    console.log("Fill Form");
    const li = e.target.closest("li");
    if(!li)
        return;

    const result = currentSearchResults[li.dataset.index];
    add_form.elements["title"].value = result.title;
    add_form.elements["format"].value = result.format;
    add_form.elements["title"].blur();
    hideSearchResults();
}

function listEntry(entry) {
    return `
        <li class="search-entry" data-index="${entry._index}">
            <img class="search-entry-image" src="${entry.imageUrl}" alt="${entry.title}">
            <div class="search-entry-info">
                <span class="search-entry-title">${entry.title}</span>
                <div class="searh-entry-meta">
                    ${entry.releaseDate ? `<span class="badge">${entry.releaseDate.split('-')[0]}</span>` : ''}
                    ${entry.chapters ? `<span class="badge">Ch: ${entry.chapters}</span>` : ''}
                    ${entry.episodes && !(entry.format === "MOVIE" || entry.format === "ANIME_MOVIE")  ? `<span class="badge">Ep: ${entry.episodes}</span>` : ''}     
                    ${entry.meanScore ? `<span class="badge">Score: ${entry.meanScore}</span>` : ''}            
                </div>
            </div>
        </li>
        `;

}

export function hideSearchResults()
{
    console.log("Hide Search Results");
    search_dropdown.style.display = "none";
}

function showSearchResults()
{
    console.log("Show Search Results");
    search_dropdown.style.display = "flex";
}

export async function onSearchFocusIn()
{
    const query = input.value;
    if (query.length < MIN_CHARS)
        return;
    if(query !== lastQuery)
        await search();
    showSearchResults();
}

export function onSearchFocusOut(e)
{
    console.log('focusout fired');
    console.log('relatedTarget:', e.relatedTarget);
    console.log('contains:', e.currentTarget.contains(e.relatedTarget));

    if(!e.currentTarget.contains(e.relatedTarget))
        hideSearchResults();
}

export function onTabClick(tab)
{
    activeTab = tab;
    const filter = TAB_FILTERS[tab];
    const filtered = filter
    ? currentSearchResults.filter(r => filter.includes(r.format))
        : currentSearchResults;

    const limit = tab === 'all' ? 3 : null;
    renderGroups(filtered, limit);
}

function groupByMediaType(results) {
    return results.reduce((acc, result) =>
    {
        const type = result.format;
        if (!acc[type])
            acc[type] = [];
        acc[type].push(result);
        return acc;
    }, {})
}