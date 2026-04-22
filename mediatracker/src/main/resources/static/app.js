import {addItem} from './mediaEntriesApi.js';
import {fetchItems} from './mediaEntriesApi.js';
import {clearFilter} from './mediaEntriesApi.js';
import * as mediaSearchApi from './mediaSearchApi.js';

const DEBOUNCE_MS = 300;
let debounceTimer = null;

document.getElementById('add-form').addEventListener('submit', addItem);
document.getElementById('title-input').addEventListener('input', () =>
    {
        clearTimeout(debounceTimer);
        debounceTimer = setTimeout(mediaSearchApi.search, DEBOUNCE_MS);
        
    });
document.getElementById('filter-type').addEventListener('change', fetchItems);
document.getElementById('filter-status').addEventListener('change', fetchItems);

document.getElementById('filter-clear').addEventListener('click', clearFilter);

window.search = mediaSearchApi.search;

await fetchItems();