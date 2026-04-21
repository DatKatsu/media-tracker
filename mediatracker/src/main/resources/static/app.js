import {addItem} from './mediaEntriesApi.js';
import {fetchItems} from './mediaEntriesApi.js';
import {clearFilter} from './mediaEntriesApi.js';
import * as mediaSearchApi from './mediaSearchApi.js';

document.getElementById('add-form').addEventListener('submit', addItem);
document.getElementById('filter-type').addEventListener('change', fetchItems);
document.getElementById('filter-status').addEventListener('change', fetchItems);

document.getElementById('filter-clear').addEventListener('click', clearFilter);

window.fetchMedia = mediaSearchApi.search;

await fetchItems();