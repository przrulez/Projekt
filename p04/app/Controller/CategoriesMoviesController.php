<?php
App::uses('AppController', 'Controller');
/**
 * CategoriesMovies Controller
 *
 * @property CategoriesMovie $CategoriesMovie
 */
class CategoriesMoviesController extends AppController {

/**
 * index method
 *
 * @return void
 */
	public function index() {
		$this->CategoriesMovie->recursive = 0;
		$this->set('categoriesMovies', $this->paginate());
	}

/**
 * view method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function view($id = null) {
		$this->CategoriesMovie->id = $id;
		if (!$this->CategoriesMovie->exists()) {
			throw new NotFoundException(__('Invalid categories movie'));
		}
		$this->set('categoriesMovie', $this->CategoriesMovie->read(null, $id));
	}

/**
 * add method
 *
 * @return void
 */
	public function add() {
		if ($this->request->is('post')) {
			$this->CategoriesMovie->create();
			if ($this->CategoriesMovie->save($this->request->data)) {
				$this->Session->setFlash(__('The categories movie has been saved'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('The categories movie could not be saved. Please, try again.'));
			}
		}
		$categories = $this->CategoriesMovie->Category->find('list');
		$movies = $this->CategoriesMovie->Movie->find('list');
		$this->set(compact('categories', 'movies'));
	}

/**
 * edit method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function edit($id = null) {
		$this->CategoriesMovie->id = $id;
		if (!$this->CategoriesMovie->exists()) {
			throw new NotFoundException(__('Invalid categories movie'));
		}
		if ($this->request->is('post') || $this->request->is('put')) {
			if ($this->CategoriesMovie->save($this->request->data)) {
				$this->Session->setFlash(__('The categories movie has been saved'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('The categories movie could not be saved. Please, try again.'));
			}
		} else {
			$this->request->data = $this->CategoriesMovie->read(null, $id);
		}
		$categories = $this->CategoriesMovie->Category->find('list');
		$movies = $this->CategoriesMovie->Movie->find('list');
		$this->set(compact('categories', 'movies'));
	}

/**
 * delete method
 *
 * @throws MethodNotAllowedException
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function delete($id = null) {
		if (!$this->request->is('post')) {
			throw new MethodNotAllowedException();
		}
		$this->CategoriesMovie->id = $id;
		if (!$this->CategoriesMovie->exists()) {
			throw new NotFoundException(__('Invalid categories movie'));
		}
		if ($this->CategoriesMovie->delete()) {
			$this->Session->setFlash(__('Categories movie deleted'));
			$this->redirect(array('action' => 'index'));
		}
		$this->Session->setFlash(__('Categories movie was not deleted'));
		$this->redirect(array('action' => 'index'));
	}
}
