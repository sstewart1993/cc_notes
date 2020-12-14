const assert = require('assert');
const PaintCan = require('../paint_can.js');

describe('Paint Can', function () {

  let paintCan;

  beforeEach(function () {
    paintCan = new PaintCan(10);
  });

  xit('should have a number of litres of paint', function () {
    const expected = 10;
    assert.strictEqual(paintCan.litresOfPaint, expected);
  });

  xit('should not start empty', function () {
    const expected = false;
    assert.strictEqual(paintCan.isEmpty(), expected);
  });

  xit('should be able to empty itself', function () {
    paintCan.empty();
    const expected = true;
    assert.strictEqual(paintCan.isEmpty(), expected);
  });

});
