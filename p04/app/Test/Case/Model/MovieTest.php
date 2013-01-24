<?php
App::uses('Movie', 'Model');

/**
 * Movie Test Case
 *
 */
class MovieTest extends CakeTestCase {

/**
 * Fixtures
 *
 * @var array
 */
	public $fixtures = array(
		'app.movie',
		'app.category',
		'app.categories_movie',
		'app.user',
		'app.users_category',
		'app.playlist',
		'app.playlist_movie',
		'app.users_movie'
	);

/**
 * setUp method
 *
 * @return void
 */
	public function setUp() {
		parent::setUp();
		$this->Movie = ClassRegistry::init('Movie');
	}

/**
 * tearDown method
 *
 * @return void
 */
	public function tearDown() {
		unset($this->Movie);

		parent::tearDown();
	}

}
