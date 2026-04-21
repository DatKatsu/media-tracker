import {addItem} from './fetchApi.js';
import {fetchItems} from './fetchApi.js';
import {clearFilter} from './fetchApi.js';
import * as searchApi from './searchApi.js';

document.getElementById('add-form').addEventListener('submit', addItem);
document.getElementById('filter-type').addEventListener('change', fetchItems);
document.getElementById('filter-status').addEventListener('change', fetchItems);

document.getElementById('filter-clear').addEventListener('click', clearFilter);

window.fetchMedia = searchApi.fetchMedia;

fetchItems();