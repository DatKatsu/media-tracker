const API_URL = '/api/items';

export async function fetchItems() {
    const typeFilter = document.getElementById('filter-type').value;
    const statusFilter = document.getElementById('filter-status').value;

    const params = new URLSearchParams();
    if(typeFilter) params.append('type', typeFilter);
    if(statusFilter) params.append('status', statusFilter);

    const response = await fetch(`${API_URL}?${params}`);
    const items = await response.json();

    renderItems(items);
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
            <td>${formatEnum(item.type)}</td>
            <td>
                <select onchange="updateStatus(${item.id}, this.value)">
                    ${statusOptions(item.status)}
                </select>
            </td>
            <td contenteditable="true" onblur="updateNotes(${item.id}, this.innerText)">${item.notes || ''}</td>
            <td>
                <button aria-label="Delete ${item.title}" class="delete-btn" onclick="deleteItem(${item.id})">Delete</button>
            </td>
        `;
        tbody.appendChild(row);
    });
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
        type: form.type.value,
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

export async function deleteItem(id) {
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
    const typeFilter = document.getElementById('filter-type');
    const statusFilter = document.getElementById('filter-status');
    typeFilter.value = "";
    statusFilter.value = "";
}


