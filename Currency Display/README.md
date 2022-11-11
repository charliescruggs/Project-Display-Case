This python script is a web scraping utility for pulling wikipedia tables into a local CSV file.

I Utilized some publically availble Python libraries in order to accomplish this including:

Requests - This library has a method called ‘urlopen’ which was used to retrieve the initial HTML data stream from the wikipedia URL in my program. 

BeautifulSoup4 -  This useful library helped me parse the data by html tags in the website’s datastream.

Pandas - This library has a dataframe functionality that was used to store the table information. Pandas also has a to_csv function that allows me to export this dataframe as a CSV.
