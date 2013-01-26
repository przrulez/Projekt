<?php
App::uses('UsersCategory', 'Model');

/**
 * UsersCategory Test Case
 *
 */
class UsersCategoryTest extends CakeTestCase {

/**
 * Fixtures
 *
 * @var array
 */
	public $fixtures = array(
		'app.users_category',
		'app.user',
		'app.playlist',
		'app.playlist_movie',
		'app.category',
		'app.movie',
		'app.categories_movie',
		'app.users_movie'
	);

/**
 * setUp method
 *
 * @return void
 */
	public function setUp() {
		parent::setUp();
		$this->UsersCategory = ClassRegistry::init('UsersCategory');
	}

/**
 * tearDown method
 *
 * @return void
 */
	public function tearDown() {
		unset($this->UsersCategory);

		parent::tearDown();
	}

}
