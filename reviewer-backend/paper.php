<?php
    class Paper {
        public $id;
        public $title;
        public $abstract;

        public function __construct($id, $title, $abstract) {
            $this->id = $id;
            $this->title = $title;
            $this->abstract = $abstract;
        }

        public function to_string() {
            return "Paper: " . "id = " . $this->id . " title = " . $this->title . " abstract  = " . $this->abstract;
        }
    }
?>