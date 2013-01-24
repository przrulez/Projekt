<div class="playlistMovies view">
<h2><?php  echo __('Playlist Movie'); ?></h2>
	<dl>
		<dt><?php echo __('Id'); ?></dt>
		<dd>
			<?php echo h($playlistMovie['PlaylistMovie']['id']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Playlist'); ?></dt>
		<dd>
			<?php echo $this->Html->link($playlistMovie['Playlist']['name'], array('controller' => 'playlists', 'action' => 'view', $playlistMovie['Playlist']['id'])); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Movie'); ?></dt>
		<dd>
			<?php echo $this->Html->link($playlistMovie['Movie']['name'], array('controller' => 'movies', 'action' => 'view', $playlistMovie['Movie']['id'])); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Created'); ?></dt>
		<dd>
			<?php echo h($playlistMovie['PlaylistMovie']['created']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Modified'); ?></dt>
		<dd>
			<?php echo h($playlistMovie['PlaylistMovie']['modified']); ?>
			&nbsp;
		</dd>
	</dl>
</div>
<div class="actions">
	<h3><?php echo __('Actions'); ?></h3>
	<ul>
		<li><?php echo $this->Html->link(__('Edit Playlist Movie'), array('action' => 'edit', $playlistMovie['PlaylistMovie']['id'])); ?> </li>
		<li><?php echo $this->Form->postLink(__('Delete Playlist Movie'), array('action' => 'delete', $playlistMovie['PlaylistMovie']['id']), null, __('Are you sure you want to delete # %s?', $playlistMovie['PlaylistMovie']['id'])); ?> </li>
		<li><?php echo $this->Html->link(__('List Playlist Movies'), array('action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Playlist Movie'), array('action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Playlists'), array('controller' => 'playlists', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Playlist'), array('controller' => 'playlists', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Movies'), array('controller' => 'movies', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Movie'), array('controller' => 'movies', 'action' => 'add')); ?> </li>
	</ul>
</div>
