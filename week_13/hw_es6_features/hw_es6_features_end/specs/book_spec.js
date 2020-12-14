import assert from 'assert';
import Book from '../book';

describe('Book', function () {
  let book;

  beforeEach(function () {
    book = new Book('Charles Dickens', 'A Tale of Two Cities', 'Historical');
  });

  it('should have a title', function () {
    assert.strictEqual(book.title, 'A Tale of Two Cities');
  });

  it('should have an author', function () {
    assert.strictEqual(book.author, 'Charles Dickens');
  });

  it('should have a genre', function () {
    assert.strictEqual(book.genre, 'Historical');
  });
});
