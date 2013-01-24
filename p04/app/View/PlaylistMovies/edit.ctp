<div class="playlistMovies form">
<?php echo $this->Form->create('PlaylistMovie'); ?>
	<fieldset>
		<legend><?php echo __('Edit Playlist Movie'); ?></legend>
	<?php
		echo $this->Form->input('id');
		echo $this->Form->input('playlist_id');
		echo $this->Form->input('movie_id');
	?>
	</fieldset>
<?php echo $this->Form->end(__('Submit')); ?>
</div>
<div class="actions">
	<h3><?php echo __('Actions'); ?></h3>
	<ul>

		<li><?php echo $this->Form->postLink(__('Delete'), array('action' => 'delete', $this->Form->value('PlaylistMovie.id')), null, __('Are you sure you want to delete # %s?', $this->Form->value('PlaylistMovie.id'))); ?></li>
		<li><?php echo $this->Html->link(__('List Playlist Movies'), array('action' => 'index')); ?></li>
		<li><?php echo $this->Html->link(__('List Playlists'), array('controller' => 'playlists', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Playlist'), array('controller' => 'playlists', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Movies'), array('controller' => 'movies', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Movie'), array('controller' => 'movies', 'action' => 'add')); ?> </li>
	</ul>
</div>
