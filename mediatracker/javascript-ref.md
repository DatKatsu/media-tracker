# JavaScript Reference — Coming from Java/C#

## Key Differences from Java/C#

- **Multi-paradigm** — not purely OOP. You can write procedural, OOP, or functional style
- **Dynamically typed** — variables have no declared type, values do
- **Weakly typed** — silently converts types when mixing them
- **Single threaded** — only does one thing at a time, uses async patterns for waiting
- **Runs in the browser** — originally designed for web pages, not large applications

---

## Variables

```javascript
var name = "hello";   // OLD — avoid, has confusing scoping rules
let name = "hello";   // use for variables that change
const name = "hello"; // use for variables that don't change (default choice)
```

**Rule of thumb:** use `const` for everything, change to `let` only if you need to reassign.

`const` on objects/arrays doesn't mean truly immutable — the contents can still change,
just the variable can't point to a different object:

```javascript
const mediaEntry = { title: "Berserk" };
mediaEntry.title = "Elden Ring"; // fine — modifying contents
mediaEntry = { title: "Elden Ring" }; // error — reassigning variable
```

---

## Types

### Primitives
- `string` — text, e.g. `"hello"`
- `number` — all numbers (no int/float/double distinction), e.g. `42`, `3.14`
- `boolean` — `true` or `false`
- `null` — explicitly set to "no value"
- `undefined` — declared but never assigned
- `bigint` — very large integers
- `symbol` — unique identifiers (advanced, rarely needed)

Everything else is an object — arrays, functions, dates, custom objects.

### null vs undefined
```javascript
let x;          // x is undefined — never assigned
let y = null;   // y is explicitly null — intentionally empty
```

---

## Weak Typing — Type Coercion

JavaScript silently converts types when mixing them:

```javascript
"5" + 3    // "53" — number converted to string, concatenated
"5" - 3    // 2 — string converted to number, subtracted
"5" == 5   // true — loose equality, converts before comparing
"5" === 5  // false — strict equality, no conversion
```

**Always use `===` (strict equality), never `==`.**
Using `==` causes subtle hard-to-find bugs.

---

## Objects (like structs / HashMaps)

Key-value pairs, no class definition needed:

```javascript
const mediaEntry = {
    title: "Berserk",
    type: "MANGA",
    status: "INTERESTED"
};

mediaEntry.title;         // "Berserk" — dot notation
mediaEntry["title"];      // "Berserk" — bracket notation (useful when key is a variable)
mediaEntry.notes = "Great"; // add new field
delete mediaEntry.notes;    // remove field
```

---

## Arrays

Dynamic size — no fixed length like Java arrays:

```javascript
const fruits = ["apple", "banana", "cherry"];

fruits[0];              // "apple"
fruits.length;          // 3
fruits.push("date");    // add to end
fruits.pop();           // remove from end
fruits[0] = "avocado";  // replace element
```

### Important Array Methods

```javascript
const items = [
    { title: "Berserk", type: "MANGA" },
    { title: "Elden Ring", type: "GAME" },
    { title: "Vinland Saga", type: "ANIME" }
];

// forEach — loop through every element, no return value
items.forEach(mediaEntry => {
    console.log(mediaEntry.title);
});

// map — transform every element, returns new array
const titles = items.map(mediaEntry => mediaEntry.title);
// ["Berserk", "Elden Ring", "Vinland Saga"]

// filter — keep only elements matching condition, returns new array
const games = items.filter(mediaEntry => mediaEntry.type === "GAME");
// [{ title: "Elden Ring", type: "GAME" }]

// find — returns first element matching condition
const berserk = items.find(mediaEntry => mediaEntry.title === "Berserk");
// { title: "Berserk", type: "MANGA" }
```

---

## Functions

Three ways to write functions:

```javascript
// Classic function declaration
function greet(name) {
    return "Hello " + name;
}

// Function expression — assigned to variable
const greet = function(name) {
    return "Hello " + name;
}

// Arrow function — modern shorthand, most common today
const greet = (name) => {
    return "Hello " + name;
}

// Arrow function — short form when body is a single expression
const greet = name => "Hello " + name;
```

Arrow functions are the most common in modern JavaScript.

---

## Template Literals (String Interpolation)

Use backticks instead of quotes, `${}` for expressions:

```javascript
const title = "Berserk";
const type = "MANGA";

// Old way
const str = "Title: " + title + ", Type: " + type;

// Template literal
const str = `Title: ${title}, Type: ${type}`;

// Works with any expression
const str = `2 + 2 = ${2 + 2}`;
```

---

## Classes

```javascript
class Item {
    constructor(title, type, status) {
        this.title = title;
        this.type = type;
        this.status = status;
    }

    describe() {
        return `${this.title} - ${this.type}`;
    }
}

const mediaEntry = new Item("Berserk", "MANGA", "INTERESTED");
mediaEntry.describe(); // "Berserk - MANGA"
```

Similar to Java but less common in plain JavaScript — modern code often uses plain objects
and functions instead. Classes are more common in frameworks like React.

---

## async / await

JavaScript is single threaded. Fetching data from a server takes time.
Instead of freezing the page, JavaScript uses asynchronous programming.

`async` marks a function as asynchronous.
`await` pauses execution inside that function until the promise resolves —
without freezing the rest of the page.

```javascript
async function fetchItems() {
    const response = await fetch('/api/items'); // wait for HTTP response
    const items = await response.json();        // wait for JSON parsing
    renderItems(items);
}
```

You can only use `await` inside an `async` function.

---

## Common Built-ins

### console
```javascript
console.log("hello");          // general logging
console.error("went wrong");   // error logging
```

### JSON
```javascript
JSON.stringify({ title: "Berserk" }); // object → JSON string: '{"title":"Berserk"}'
JSON.parse('{"title":"Berserk"}');    // JSON string → object: { title: "Berserk" }
```

### document (browser only — manipulates HTML)
```javascript
document.getElementById('items-body');    // find element by id
document.createElement('tr');             // create new element
element.innerHTML = '<td>hello</td>';     // set inner HTML content
element.appendChild(childElement);        // add child element
```

### fetch (browser only — makes HTTP requests)
```javascript
// GET request
const response = await fetch('/api/items');
const data = await response.json();

// POST request with JSON body
const response = await fetch('/api/items', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ title: "Berserk", type: "MANGA" })
});

// PATCH request
const response = await fetch(`/api/items/${id}`, {
    method: 'PATCH',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ status: "COMPLETED" })
});

// DELETE request
await fetch(`/api/items/${id}`, { method: 'DELETE' });
```

---

## Key Differences Summary vs Java

| Java | JavaScript |
|------|------------|
| `int x = 5;` | `const x = 5;` |
| `String s = "hi";` | `const s = "hi";` |
| `int[] arr = {1,2,3};` | `const arr = [1,2,3];` |
| `arr.length` | `arr.length` |
| `System.out.println()` | `console.log()` |
| `==` (value equality) | `===` (use this always) |
| `.equals()` (string compare) | `===` |
| `null` | `null` and `undefined` |
| `int`, `float`, `double`, `long` | just `number` |
| `char` | just a string of length 1 |
| Classes required | Plain objects and functions work fine |
| Checked exceptions | No checked exceptions |
| Static typing | Dynamic typing |
| Strong typing | Weak typing (avoid `==`) |
