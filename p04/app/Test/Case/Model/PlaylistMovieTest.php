<?php
App::uses('PlaylistMovie', 'Model');

/**
 * PlaylistMovie Test Case
 *
 */
class PlaylistMovieTest extends CakeTestCase {

/**
 * Fixtures
 *
 * @var array
 */
	public $fixtures = array(
		'app.playlist_movie',
		'app.playlist',
		'app.user',
		'app.category',
		'app.movie',
		'app.categories_movie',
		'app.users_movie',
		'app.users_category'
	);

/**
 * setUp method
 *
 * @return void
 */
	public function setUp() {
		parent::setUp();
		$this->PlaylistMovie = ClassRegistry::init('PlaylistMovie');
	}

/**
 * tearDown method
 *
 * @return void
 */
	public function tearDown() {
		unset($this->PlaylistMovie);

		parent::tearDown();
	}

}
