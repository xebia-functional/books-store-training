CREATE TABLE IF NOT EXISTS User(
    id UUID PRIMARY KEY,
    name TEXT
);

CREATE TABLE IF NOT EXISTS Book(
    id UUID PRIMARY KEY,
    title TEXT,
    author TEXT,
    date DATE,
    available BOOLEAN
);

CREATE TABLE IF NOT EXISTS Register(
    user_id UUID,
    book_id UUID,
    rentDate DATE NOT NULL,
    returnDate DATE,
    PRIMARY KEY (user_id, book_id),
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (book_id) REFERENCES Book(id)
);