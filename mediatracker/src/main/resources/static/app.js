const API_URL = '/api/items';

async function fetchItems() {
    const typeFilter = document.getElementById('filter-type').value;
    const statusFilter = document.getElementById('filter-status').value;

    const response = await fetch(API_URL);
    const items = await response.json();

    const filtered = items.filter(item => {
        const matchesType = typeFilter === '' || item.type === typeFilter;
        const matchesStatus = statusFilter === '' || item.status === statusFilter;
        return matchesType && matchesStatus;
    });

    renderItems(filtered);
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
            <td>${item.notes || ''}</td>
            <td>
                <button class="delete-btn" onclick="deleteItem(${item.id})">Delete</button>
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

async function addItem(event) {
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
    fetchItems();
}

async function updateStatus(id, status) {
    await fetch(`${API_URL}/${id}`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ status })
    });

    fetchItems();
}

async function deleteItem(id) {
    await fetch(`${API_URL}/${id}`, {
        method: 'DELETE'
    });

    fetchItems();
}

document.getElementById('add-form').addEventListener('submit', addItem);
document.getElementById('filter-type').addEventListener('change', fetchItems);
document.getElementById('filter-status').addEventListener('change', fetchItems);

fetchItems();