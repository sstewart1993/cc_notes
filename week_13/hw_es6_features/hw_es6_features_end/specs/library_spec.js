import assert from 'assert';
import Library from '../library';
import Book from '../book';

describe('Library', function () {
  let library;

  beforeEach(function () {
    library = new Library();
  });

  it('should start with no books if no books given', function () {
    assert.strictEqual(library.bookCount(), 0)
  });

  it('should start with books if books given', function () {
    const books = [
      new Book('J R R Tolkien', 'The Fellowship of the Ring', 'Fantasy'),
      new Book('J R R Tolkien', 'The Two Towers', 'Fantasy'),
      new Book('J R R Tolkien', 'The Return of the King', 'Fantasy')
    ];
    const libraryWithBooks = new Library(books);
    assert.strictEqual(libraryWithBooks.bookCount(), 3);
  });

  it('should be able to add a book', function () {
    assert.strictEqual(library.bookCount(), 0);
    const book = new Book('J R R Tolkien', 'The Hobbit', 'Fantasy');
    library.addBook(book);
    assert.strictEqual(library.bookCount(), 1);
  });

  it('should be able to add multiple books', function () {
    assert.strictEqual(library.bookCount(), 0);
    const books = [
      new Book('J R R Tolkien', 'The Fellowship of the Ring', 'Fantasy'),
      new Book('J R R Tolkien', 'The Two Towers', 'Fantasy'),
      new Book('J R R Tolkien', 'The Return of the King', 'Fantasy')
    ];
    library.addBooks(books);
    assert.strictEqual(library.bookCount(), 3);
  });
});
