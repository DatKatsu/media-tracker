const API_SEARCH_URL = '/api/search';

const MIN_CHARS = 3;
const MAX_RESULTS = 10;


let currentSearchResults = [];

const input = document.getElementById('title-input');
const search_results = document.getElementById('search-results');
const search_container = document.getElementById('search-container');
const add_form = document.getElementById('add-form');

export async function search() {

    const query = input.value;
    if (query.length < MIN_CHARS) {
        hideSearchResults();
        return;
    }
    const params = new URLSearchParams();
    //const testQuery = 'One';
    params.append('query', query);

    const response = await fetch(`${API_SEARCH_URL}?${params}`);
    const results = await response.json();


    renderSearchResults(results, query)
    console.log(results);
}

function renderSearchResults(results, query) {

    currentSearchResults = results;
    const groupedResults = groupByMediaType(results);
    console.log(groupedResults);

    let columnHTML = ``;
    Object.entries(groupedResults).forEach(([type, entries]) => {
        columnHTML += `
        <div class="search-column"> 
            <h3 class="search-entry-header">${type}</h3>
            <ul class="search-column-list">
                ${entries.map(entry => listEntry(entry)).join('')}
            </ul>
        </div>
        `
    });


    search_results.innerHTML = columnHTML;
    document.querySelector(".search-column-list").addEventListener('mousedown', fillForm);

    showSearchResults();
}

function fillForm(e)
{
    e.preventDefault();
    console.log("Fill Form");
    hideSearchResults();
}

function listEntry(entry) {
    return `
        <li class="search-entry">
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

export function showSearchResults()
{
    const query = input.value;
    if (query.length < MIN_CHARS) {
        return;
    }

    console.log("Show Search Results");
    search_results.style.display = "flex";
}

function groupByMediaType(results) {
    return results.reduce((acc, result) =>
    {
        const type = result.type;
        if (!acc[type])
            acc[type] = []
        acc[type].push(result);
        return acc;
    }, {})
}