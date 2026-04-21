const API_SEARCH_URL = '/api/search';
const DEBOUNCE_MS = 300;
const MIN_CHARS = 3;
const MAX_RESULTS = 10;

let debounceTimer = null;
let currentResults = [];

const input = document.getElementById('title');
const search_results = document.getElementById('search-results');
const search_container = document.getElementById('search-container');

export async function search() {
    const params = new URLSearchParams();
    const testQuery = 'One';
    params.append('query', testQuery);

    const response = await fetch(`${API_SEARCH_URL}?${params}`);
    const results = await response.json();
    renderResults(results, testQuery)
    console.log(results);
}

function renderResults(results, query) {

    search_results.style.display = "grid";
    search_results.innerHTML = `<li>No results</li><li>No results</li><li>No results</li><li>No results</li><li>No results</li>`;

    //results.forEach(buildSuggestionList(results));
    //search_results.innerHTML = `<>`


}

function buildSuggestionList(results) {

}