<div class="playlists form">
<?php echo $this->Form->create('Playlist'); ?>
	<fieldset>
		<legend><?php echo __('Add Playlist'); ?></legend>
	<?php
		echo $this->Form->input('name');
		echo $this->Form->input('user_id');
	?>
	</fieldset>
<?php echo $this->Form->end(__('Submit')); ?>
</div>
<div class="actions">
	<h3><?php echo __('Actions'); ?></h3>
	<ul>

		<li><?php echo $this->Html->link(__('List Playlists'), array('action' => 'index')); ?></li>
		<li><?php echo $this->Html->link(__('List Users'), array('controller' => 'users', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New User'), array('controller' => 'users', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Playlist Movies'), array('controller' => 'playlist_movies', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Playlist Movie'), array('controller' => 'playlist_movies', 'action' => 'add')); ?> </li>
	</ul>
</div>
