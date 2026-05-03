import {addItem} from './mediaEntriesApi.js';
import {fetchItems} from './mediaEntriesApi.js';
import {clearFilter} from './mediaEntriesApi.js';
import * as mediaSearchApi from "./mediaSearchApi.js";

const DEBOUNCE_MS = 300
let debounceTimer = null;

const add_form = document.getElementById('add-form');
const title_input = document.getElementById('title-input');
const search_container = document.getElementById('search-container');
const search_results = document.getElementById('search-results');
const format_filter = document.getElementById('filter-format');
const status_filter = document.getElementById('filter-status');
const clear_filter = document.getElementById('filter-clear');

add_form.addEventListener('submit', addItem);

title_input.addEventListener('input', () =>
    {
        clearTimeout(debounceTimer);
        console.log('cleared timer', debounceTimer);
        debounceTimer = setTimeout(mediaSearchApi.search, DEBOUNCE_MS);
        console.log('set new timer', debounceTimer);
    });
title_input.addEventListener('focus', mediaSearchApi.onSearchFocusIn);
search_container.addEventListener('focusout', mediaSearchApi.onSearchFocusOut);
search_results.addEventListener('mousedown', mediaSearchApi.fillForm);
format_filter.addEventListener('change', fetchItems);
status_filter.addEventListener('change', fetchItems);
clear_filter.addEventListener('click', clearFilter);

document.querySelectorAll('.tab').forEach(tab => {
    tab.addEventListener('click', () => {
        document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
        tab.classList.add('active');
        mediaSearchApi.onTabClick(tab.dataset.tab);
    });
});

window.search = mediaSearchApi.search;
await fetchItems();