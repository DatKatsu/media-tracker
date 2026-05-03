

const API_URL = '/api/media';

export async function fetchItems() {
    const formatFilter = document.getElementById('filter-format').value;
    const statusFilter = document.getElementById('filter-status').value;

    const params = new URLSearchParams();
    if(formatFilter) params.append('format', formatFilter);
    if(statusFilter) params.append('status', statusFilter);

    try {
        const response = await fetch(`${API_URL}?${params}`);
        const items = await response.json();

        renderItems(items);
    } catch (error)
    {
        console.error("Fetching items failed: ", error);
    }
}

function renderItems(items) {
    const tbody = document.getElementById('items-body');
    tbody.innerHTML = '';

    if (items.length === 0) {
        tbody.innerHTML = '<tr><td colspan="5">No items found.</td></tr>';
        return;
    }

    items.forEach(item => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${item.title}</td>
            <td>${formatEnum(item.format)}</td>
            <td>
                <select class="entry-status" data-id="${item.id}">
                    ${statusOptions(item.status)}
                </select>
            </td>
            <td contenteditable="true" class="entry-notes" data-id="${item.id}">${item.notes || ''}</td>
            <td>
                <button aria-label="Delete ${item.title}" class="entry-delete-btn" data-id="${item.id}">Delete</button>
            </td>
        `;
        tbody.appendChild(row);

        tbody.removeEventListener('click', onDeleteClick)
        tbody.removeEventListener('change', onStatusChange);
        tbody.removeEventListener('focusout', onUpdateNotes)

        tbody.addEventListener('click', onDeleteClick);
        tbody.addEventListener('change', onStatusChange);
        tbody.addEventListener('focusout', onUpdateNotes)
    });
}

async function onStatusChange(e)
{
    const entryStatus = e.target.closest('.entry-status');
    if(!entryStatus)
        return;
    const id = entryStatus.dataset.id;
    await updateStatus(id, entryStatus.value);
}

async function onDeleteClick(e)
{
    const deleteButton = e.target.closest('.entry-delete-btn');
    if(!deleteButton)
        return;
    const id = deleteButton.dataset.id;
    await deleteItem(id);
}

async function onUpdateNotes(event)
{
    event.preventDefault();
    const entryNotes = event.target.closest('.entry-notes');
    if(!entryNotes)
        return;
    const id = entryNotes.dataset.id;
    await updateNotes(id, entryNotes.innerText);
}

function statusOptions(current) {
    const statuses = ['INTERESTED', 'IN_PROGRESS', 'COMPLETED', 'DROPPED'];
    return statuses.map(s =>
        `<option value="${s}"${s === current ? 'selected' : ''}>${formatEnum(s)}</option>`
    ).join('');
}

function formatEnum(value) {
    return value.replace(/_/g, ' ').toLowerCase()
        .replace(/\b\w/g, c => c.toUpperCase());
}

export async function addItem(event) {
    event.preventDefault();
    const form = event.target;

    const item = {
        title: form.title.value,
        format: form.format.value,
        status: form.status.value,
        notes: form.notes.value || null
    };

    await fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(item)
    });

    form.reset();
    await fetchItems();
}

async function updateStatus(id, status) {
    await fetch(`${API_URL}/${id}`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ status })
    });

    await fetchItems();
}

async function updateNotes(id, notes) {
    await fetch(`${API_URL}/${id}`, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ notes })
    });
    await fetchItems();
}

async function deleteItem(id) {
    await fetch(`${API_URL}/${id}`, {
        method: 'DELETE'
    });

    await fetchItems();
}


export async function clearFilter()
{
    resetSelectionDropDown();
    await fetchItems();
}

function resetSelectionDropDown()
{
    const formatFilter = document.getElementById('filter-format');
    const statusFilter = document.getElementById('filter-status');
    formatFilter.value = "";
    statusFilter.value = "";
}


