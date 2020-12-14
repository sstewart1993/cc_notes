const assert = require('assert');
const Room = require('../room.js');

describe('Room', function () {

  let room;

  beforeEach(function () {
    room = new Room(20);
  });

  xit('should have an area in square meters', function () {
    const expected = 20;
    assert.strictEqual(room.areaInSquareMeters, expected);
  });

  xit('should start not painted', function () {
    const expected = false;
    assert.strictEqual(room.isPainted, expected);
  });

  xit('should be able to be painted', function () {
    room.paint();
    const expected = true;
    assert.strictEqual(room.isPainted, expected);
  });

});
