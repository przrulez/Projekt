<?php
App::uses('UsersMovie', 'Model');

/**
 * UsersMovie Test Case
 *
 */
class UsersMovieTest extends CakeTestCase {

/**
 * Fixtures
 *
 * @var array
 */
	public $fixtures = array(
		'app.users_movie',
		'app.users',
		'app.movies'
	);

/**
 * setUp method
 *
 * @return void
 */
	public function setUp() {
		parent::setUp();
		$this->UsersMovie = ClassRegistry::init('UsersMovie');
	}

/**
 * tearDown method
 *
 * @return void
 */
	public function tearDown() {
		unset($this->UsersMovie);

		parent::tearDown();
	}

}
