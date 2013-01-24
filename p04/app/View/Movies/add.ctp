<div class="movies form">
<?php echo $this->Form->create('Movie'); ?>
	<fieldset>
		<legend><?php echo __('Add Movie'); ?></legend>
	<?php
		echo "
			<!-- Load Queue widget CSS and jQuery -->
			<link rel='stylesheet' type='text/css' href='http://localhost/p04/plupload/js/jquery.plupload.queue/css/jquery.plupload.queue.css' />
			<script type='text/javascript' src='https://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js'></script>

			<!-- Third party script for BrowserPlus runtime (Google Gears included in Gears runtime now) -->
			<script type='text/javascript' src='http://bp.yahooapis.com/2.4.21/browserplus-min.js'></script>

			<!-- Load plupload and all it's runtimes and finally the jQuery queue widget -->
			<script type='text/javascript' src='http://localhost/p04/plupload/js/plupload.full.js'></script>
			<script type='text/javascript' src='http://localhost/p04/plupload/js/jquery.plupload.queue/jquery.plupload.queue.js'></script>

			<script type='text/javascript'>
			// Convert divs to queue widgets when the DOM is ready
			$(function() {
				$('#uploader').pluploadQueue({
					// General settings
					runtimes : 'gears,browserplus,html5',
					url : 'http://localhost/p04/plupload/upload.php',
					max_file_size : '10000mb',
					chunk_size : '1mb',
					unique_names : false,

					// Resize images on clientside if we can
					//resize : {width : 320, height : 240, quality : 90},

					// Specify what files to browse for
					filters : [
						{title : 'Movie files', extensions : 'webm,ogg,mp4'},
					],

					// Flash settings
					flash_swf_url : 'http://localhost/p04/plupload/js/plupload.flash.swf',

					// Silverlight settings
					silverlight_xap_url : 'http://localhost/p04/plupload/js/plupload.silverlight.xap'
				});

				// Client side form validation
				/*
				$('form').submit(function(e) {
					var uploader = $('#uploader').pluploadQueue();

					// Files in queue upload them first
					if (uploader.files.length > 0) {
						// When all files are uploaded submit form
						uploader.bind('StateChanged', function() {
							if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
								$('form')[0].submit();
							}
						});
							
						uploader.start();
					} else {
						alert('You must queue at least one file.');
					}

					return false;
				});
				*/
			});
			</script>


			<div id='uploader' class='uploader'>
				<p>You browser doesn't have Flash, Silverlight, Gears, BrowserPlus or HTML5 support.</p>
			</div>";
			
		echo $this->Form->input('name');
		echo $this->Form->input('content');
		echo $this->Form->input('file');
		echo $this->Form->input('local');
		echo $this->Form->input('server');
		echo $this->Form->input('Category');
		echo $this->Form->input('Playlist');
		echo $this->Form->input('User');
	?>
	</fieldset>
<?php echo $this->Form->end(__('Submit')); ?>
</div>
<div class='actions'>
	<h3><?php echo __('Actions'); ?></h3>
	<ul>

		<li><?php echo $this->Html->link(__('List Movies'), array('action' => 'index')); ?></li>
		<li><?php echo $this->Html->link(__('List Categories'), array('controller' => 'categories', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Category'), array('controller' => 'categories', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Playlists'), array('controller' => 'playlists', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Playlist'), array('controller' => 'playlists', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Users'), array('controller' => 'users', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New User'), array('controller' => 'users', 'action' => 'add')); ?> </li>
	</ul>
</div>
