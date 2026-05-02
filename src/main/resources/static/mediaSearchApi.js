const API_SEARCH_URL = '/api/search';

const MIN_CHARS = 3;
const MAX_RESULTS = 10;


let currentSearchResults = [];
let lastQuery = "";

const input = document.getElementById('title-input');
const search_results = document.getElementById('search-results');
const add_form = document.getElementById('add-form');

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

    const response = await fetch(`${API_SEARCH_URL}?${params}`);
    const results = await response.json();

    console.log("results json:" );
    console.log(results);
    renderSearchResults(results)

}

function renderSearchResults(results) {

    currentSearchResults = results;
    results.forEach((result, index) => result._index = index);
    const groupedResults = groupByMediaType(results);
    console.log(groupedResults);

    let columnHTML = ``;
    Object.entries(groupedResults).forEach(([type, entries]) => {
        columnHTML += `
        <div class="search-column"> 
            <h3 class="search-column-header">${type}</h3>
            <ul class="search-column-list">
                ${entries.map(entry => listEntry(entry)).join('')}
            </ul>
        </div>
        `
    });

    search_results.innerHTML = columnHTML;
    document.querySelector(".search-results").addEventListener('mousedown', fillForm);

    showSearchResults();
}

function fillForm(e)
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
                <span class="searh-entry-meta">Placeholder</span>
            </div>
        </li>
        `;

}

export function hideSearchResults()
{
    console.log("Hide Search Results");
    search_results.style.display = "none";
}

function showSearchResults()
{
    console.log("Show Search Results");
    search_results.style.display = "flex";
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