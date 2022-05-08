<?php
    class Comment {
        public $paperId;
        public $reviewerId;
        public $content;

        public function __construct($paperId, $reviewerId, $content) {
            $this->paperId = $paperId;
            $this->reviewerId = $reviewerId;
            $this->content = $content;
        }
    }
?>