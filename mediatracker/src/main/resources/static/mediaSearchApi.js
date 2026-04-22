const API_SEARCH_URL = '/api/search';

const MIN_CHARS = 3;
const MAX_RESULTS = 10;


let currentResults = [];

const input = document.getElementById('title-input');
const search_results = document.getElementById('search-results');
const search_container = document.getElementById('search-container');

export async function search() {

    const query = input.value;
    if(query.length < MIN_CHARS)
        return;

    const params = new URLSearchParams();
    //const testQuery = 'One';
    params.append('query', query);

    const response = await fetch(`${API_SEARCH_URL}?${params}`);
    const results = await response.json();
    renderResults(results, query)
    console.log(results); 
}

function renderResults(results, query) {

    search_results.style.display = "grid";
    
    let listHTML = ``; 
    results.forEach(result => {listHTML += listEntry(result)});
    search_results.innerHTML = listHTML;
}

function listEntry(entry) {
    return `<li>${entry.title}</li>`;
}