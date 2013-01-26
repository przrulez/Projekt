<?php
App::uses('CategoriesMovie', 'Model');

/**
 * CategoriesMovie Test Case
 *
 */
class CategoriesMovieTest extends CakeTestCase {

/**
 * Fixtures
 *
 * @var array
 */
	public $fixtures = array(
		'app.categories_movie',
		'app.category',
		'app.movie',
		'app.playlist',
		'app.user',
		'app.users_category',
		'app.users_movie',
		'app.playlist_movie'
	);

/**
 * setUp method
 *
 * @return void
 */
	public function setUp() {
		parent::setUp();
		$this->CategoriesMovie = ClassRegistry::init('CategoriesMovie');
	}

/**
 * tearDown method
 *
 * @return void
 */
	public function tearDown() {
		unset($this->CategoriesMovie);

		parent::tearDown();
	}

}
