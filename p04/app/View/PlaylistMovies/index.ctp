<div class="playlistMovies index">
	<h2><?php echo __('Playlist Movies'); ?></h2>
	<table cellpadding="0" cellspacing="0">
	<tr>
			<th><?php echo $this->Paginator->sort('id'); ?></th>
			<th><?php echo $this->Paginator->sort('playlist_id'); ?></th>
			<th><?php echo $this->Paginator->sort('movie_id'); ?></th>
			<th><?php echo $this->Paginator->sort('created'); ?></th>
			<th><?php echo $this->Paginator->sort('modified'); ?></th>
			<th class="actions"><?php echo __('Actions'); ?></th>
	</tr>
	<?php
	foreach ($playlistMovies as $playlistMovie): ?>
	<tr>
		<td><?php echo h($playlistMovie['PlaylistMovie']['id']); ?>&nbsp;</td>
		<td>
			<?php echo $this->Html->link($playlistMovie['Playlist']['name'], array('controller' => 'playlists', 'action' => 'view', $playlistMovie['Playlist']['id'])); ?>
		</td>
		<td>
			<?php echo $this->Html->link($playlistMovie['Movie']['name'], array('controller' => 'movies', 'action' => 'view', $playlistMovie['Movie']['id'])); ?>
		</td>
		<td><?php echo h($playlistMovie['PlaylistMovie']['created']); ?>&nbsp;</td>
		<td><?php echo h($playlistMovie['PlaylistMovie']['modified']); ?>&nbsp;</td>
		<td class="actions">
			<?php echo $this->Html->link(__('View'), array('action' => 'view', $playlistMovie['PlaylistMovie']['id'])); ?>
			<?php echo $this->Html->link(__('Edit'), array('action' => 'edit', $playlistMovie['PlaylistMovie']['id'])); ?>
			<?php echo $this->Form->postLink(__('Delete'), array('action' => 'delete', $playlistMovie['PlaylistMovie']['id']), null, __('Are you sure you want to delete # %s?', $playlistMovie['PlaylistMovie']['id'])); ?>
		</td>
	</tr>
<?php endforeach; ?>
	</table>
	<p>
	<?php
	echo $this->Paginator->counter(array(
	'format' => __('Page {:page} of {:pages}, showing {:current} records out of {:count} total, starting on record {:start}, ending on {:end}')
	));
	?>	</p>

	<div class="paging">
	<?php
		echo $this->Paginator->prev('< ' . __('previous'), array(), null, array('class' => 'prev disabled'));
		echo $this->Paginator->numbers(array('separator' => ''));
		echo $this->Paginator->next(__('next') . ' >', array(), null, array('class' => 'next disabled'));
	?>
	</div>
</div>
<div class="actions">
	<h3><?php echo __('Actions'); ?></h3>
	<ul>
		<li><?php echo $this->Html->link(__('New Playlist Movie'), array('action' => 'add')); ?></li>
		<li><?php echo $this->Html->link(__('List Playlists'), array('controller' => 'playlists', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Playlist'), array('controller' => 'playlists', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Movies'), array('controller' => 'movies', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Movie'), array('controller' => 'movies', 'action' => 'add')); ?> </li>
	</ul>
</div>
