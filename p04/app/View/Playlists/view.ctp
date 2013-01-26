<div class="playlists view">
<h2><?php  echo __('Playlist'); ?></h2>
	<dl>
		<dt><?php echo __('Id'); ?></dt>
		<dd>
			<?php echo h($playlist['Playlist']['id']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Name'); ?></dt>
		<dd>
			<?php echo h($playlist['Playlist']['name']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('User'); ?></dt>
		<dd>
			<?php echo $this->Html->link($playlist['User']['id'], array('controller' => 'users', 'action' => 'view', $playlist['User']['id'])); ?>
			&nbsp;
		</dd>
	</dl>
</div>
<div class="actions">
	<h3><?php echo __('Actions'); ?></h3>
	<ul>
		<li><?php echo $this->Html->link(__('Edit Playlist'), array('action' => 'edit', $playlist['Playlist']['id'])); ?> </li>
		<li><?php echo $this->Form->postLink(__('Delete Playlist'), array('action' => 'delete', $playlist['Playlist']['id']), null, __('Are you sure you want to delete # %s?', $playlist['Playlist']['id'])); ?> </li>
		<li><?php echo $this->Html->link(__('List Playlists'), array('action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Playlist'), array('action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Users'), array('controller' => 'users', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New User'), array('controller' => 'users', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Playlist Movies'), array('controller' => 'playlist_movies', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Playlist Movie'), array('controller' => 'playlist_movies', 'action' => 'add')); ?> </li>
	</ul>
</div>
<div class="related">
	<h3><?php echo __('Related Playlist Movies'); ?></h3>
	<?php if (!empty($playlist['PlaylistMovie'])): ?>
	<table cellpadding = "0" cellspacing = "0">
	<tr>
		<th><?php echo __('Id'); ?></th>
		<th><?php echo __('Playlist Id'); ?></th>
		<th><?php echo __('Movie Id'); ?></th>
		<th class="actions"><?php echo __('Actions'); ?></th>
	</tr>
	<?php
		$i = 0;
		foreach ($playlist['PlaylistMovie'] as $playlistMovie): ?>
		<tr>
			<td><?php echo $playlistMovie['id']; ?></td>
			<td><?php echo $playlistMovie['playlist_id']; ?></td>
			<td><?php echo $playlistMovie['movie_id']; ?></td>
			<td class="actions">
				<?php echo $this->Html->link(__('View'), array('controller' => 'playlist_movies', 'action' => 'view', $playlistMovie['id'])); ?>
				<?php echo $this->Html->link(__('Edit'), array('controller' => 'playlist_movies', 'action' => 'edit', $playlistMovie['id'])); ?>
				<?php echo $this->Form->postLink(__('Delete'), array('controller' => 'playlist_movies', 'action' => 'delete', $playlistMovie['id']), null, __('Are you sure you want to delete # %s?', $playlistMovie['id'])); ?>
			</td>
		</tr>
	<?php endforeach; ?>
	</table>
<?php endif; ?>

	<div class="actions">
		<ul>
			<li><?php echo $this->Html->link(__('New Playlist Movie'), array('controller' => 'playlist_movies', 'action' => 'add')); ?> </li>
		</ul>
	</div>
</div>
