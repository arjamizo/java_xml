<?php 
//phpinfo();

error_reporting(E_ALL);
error_reporting(0);
$xslt = new xsltProcessor();
$xslt->importStyleSheet(DomDocument::load('l2t5.xsl'));
print $xslt->transformToXML(DomDocument::load('http://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml'));
?>