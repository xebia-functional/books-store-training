ALTER TABLE Register
DROP CONSTRAINT register_users_id_fkey,
DROP CONSTRAINT register_book_id_fkey;

ALTER TABLE Register
ADD CONSTRAINT register_users_id_fk FOREIGN KEY (users_id) REFERENCES Users(id) ON DELETE CASCADE,
ADD CONSTRAINT register_book_id_fk FOREIGN KEY (book_id) REFERENCES Book(id) ON DELETE CASCADE;
