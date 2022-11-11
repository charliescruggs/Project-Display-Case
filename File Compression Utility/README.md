**How to operate:**

There are two ‘Main’ classes included in this package. 

1. JMzip (compress/zip file)
  First, the user zips a file by compiling & running JMZip.java. 
  If successful, a zipped document (zipped.jmz) will be created 
  in the project’s root folder, outside of the src folder.

2. JMUnzip (decompress/unzip file)
  This second ‘Main’ class finds this zipped file and decompresses it. 
  The user just has to compile and run this program. A decompressed 
  document file is created in the project’s root folder (unzipped.doc).
  
A sample document to be zipped is already included in this package in 
the src file. If you want to zip your own document, add it to the src 
folder and replace the relative path in the code with a path to your 
own document.

The path to change out the sample document for your own can be found 
in JMzip.java on line 43
