<?php
App::uses('AppController', 'Controller');
/**
 * PlaylistMovies Controller
 *
 * @property PlaylistMovie $PlaylistMovie
 */
class PlaylistMoviesController extends AppController {

/**
 * index method
 *
 * @return void
 */
	public function index() {
		$this->PlaylistMovie->recursive = 0;
		$this->set('playlistMovies', $this->paginate());
	}

/**
 * view method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function view($id = null) {
		$this->PlaylistMovie->id = $id;
		if (!$this->PlaylistMovie->exists()) {
			throw new NotFoundException(__('Invalid playlist movie'));
		}
		$this->set('playlistMovie', $this->PlaylistMovie->read(null, $id));
	}

/**
 * add method
 *
 * @return void
 */
	public function add() {
		if ($this->request->is('post')) {
			$this->PlaylistMovie->create();
			if ($this->PlaylistMovie->save($this->request->data)) {
				$this->Session->setFlash(__('The playlist movie has been saved'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('The playlist movie could not be saved. Please, try again.'));
			}
		}
		$playlists = $this->PlaylistMovie->Playlist->find('list');
		$movies = $this->PlaylistMovie->Movie->find('list');
		$this->set(compact('playlists', 'movies'));
	}

/**
 * edit method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function edit($id = null) {
		$this->PlaylistMovie->id = $id;
		if (!$this->PlaylistMovie->exists()) {
			throw new NotFoundException(__('Invalid playlist movie'));
		}
		if ($this->request->is('post') || $this->request->is('put')) {
			if ($this->PlaylistMovie->save($this->request->data)) {
				$this->Session->setFlash(__('The playlist movie has been saved'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('The playlist movie could not be saved. Please, try again.'));
			}
		} else {
			$this->request->data = $this->PlaylistMovie->read(null, $id);
		}
		$playlists = $this->PlaylistMovie->Playlist->find('list');
		$movies = $this->PlaylistMovie->Movie->find('list');
		$this->set(compact('playlists', 'movies'));
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
		$this->PlaylistMovie->id = $id;
		if (!$this->PlaylistMovie->exists()) {
			throw new NotFoundException(__('Invalid playlist movie'));
		}
		if ($this->PlaylistMovie->delete()) {
			$this->Session->setFlash(__('Playlist movie deleted'));
			$this->redirect(array('action' => 'index'));
		}
		$this->Session->setFlash(__('Playlist movie was not deleted'));
		$this->redirect(array('action' => 'index'));
	}
}
