#STEPS TO USE:
#1. Save the .PY file to your computer 
#2. Run the Python file the way you normally would on a Windows, OSX, Linux machine. 
#3. The program will generate output as a Text file called OUTPUT.txt which should be located in the same directory as the .PY file.


#import urllib to query a website
from urllib.request import urlopen

#the url
wikiUrl = "https://en.wikipedia.org/wiki/List_of_cryptocurrencies"

#Query the website and return it to the wikipage
wikipage = urlopen(wikiUrl)

#import the Beautiful soup which is used to parse data from the website
from bs4 import BeautifulSoup

#Parse the html in the 'wikipage' variable, and store it in
#Beautiful Soup format
bSoup = BeautifulSoup(wikipage, "html.parser")

#identify the right table
CurrencyList = bSoup.find('table', { "class" : 'wikitable sortable'})

#Store each row in a list ordered by columns A-G
A = []
B = []
C = []
D = []
E = []
F = []
G = []
#H = []

for row in CurrencyList.findAll("tr"):
	cells = row.findAll('td')
	if len(cells) == 8: #for every row
		A.append(cells[0].find(text = True))
		B.append(cells[1].find(text = True))
		C.append(cells[2].find(text = True))
		D.append(cells[3].find(text = True))
		E.append(cells[4].find(text = True))
		F.append(cells[5].find(text = True))
		G.append(cells[6].find(text = True))
		#H.append(cells[7].find(text = True))

#import pandas to convert list to data frame
import pandas as pd
df = pd.DataFrame(B, columns = ['Name'])
df['Symbol'] = C
df['Founder'] = D
df['Release'] = A
df['Hash_Algorithm'] = E
df['Blockchain_Type'] = G
df['Programming Language'] = F
#df['Notes'] = H

# print(df['Symbol'])
# print(df['Release'])
# print(df['Status'])
# print(df['Founder'])
# print(df['Hash_Algorithm'])
# print(df['Blockchain_Type'])


df.to_csv('OUTPUT.csv', encoding='utf-8', index=False)