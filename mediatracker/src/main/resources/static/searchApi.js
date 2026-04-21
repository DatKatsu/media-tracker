const API_SEARCH_URL = '/api/media';
const DEBOUNCE_MS = 300;
const MIN_CHARS = 3;
const MAX_RESULTS = 10;

let debounceTimer = null;
let currentResults = [];

const input = document.getElementById('title');
const suggestion_dropdown = document.getElementById('suggestion-dropdown');

export async function fetchMedia() 
{
    const params = new URLSearchParams();
    const testQuery = 'One';
    params.append('query', testQuery);

    const response = await fetch(`${API_SEARCH_URL}?${params}`);
    const results = await response.json();
    renderSuggestions(results, testQuery)
    console.log(results);
}

async function renderSuggestions(results, query) 
{
    if(!results.length)
    {
        suggestion_dropdown.innerHTML = `<div>No results</div>`;
        return;
    }

    
}