CREATE TABLE IF NOT EXISTS Users(
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
    users_id UUID,
    book_id UUID,
    rentDate DATE NOT NULL,
    returnDate DATE,
    PRIMARY KEY (users_id, book_id, rentDate),
    FOREIGN KEY (users_id) REFERENCES Users(id),
    FOREIGN KEY (book_id) REFERENCES Book(id)
);