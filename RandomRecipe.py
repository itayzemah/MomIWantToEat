import pymongo as myDB
import random

myclient = myDB.MongoClient('localhost:27017')
mydb = myclient['mydb']
mycollection = mydb["recipe"]
ran = mycollection.find().limit( 1 ).skip( random.randint(0, mycollection.estimated_document_count()-1))
for rec in ran:
    l1 = []
    l2 = []
    print(rec['name'])
    lst1 = rec['ingredients names']
    for x in lst1:
        if x.strip() is not '' or not ' ':
            l1.append(x)
    print(l1)
    lst2 = rec['ingredients quantities']
    print(lst2)
    print(rec['preparation'])




# x =mydb.mycollection.find({"ingredients": {'$regex': '/m/'}})
# print(x)
# print(list(mycollection.find()))
#    mydoc = list(mycollection.find({"ingredients": {"$all": ingredientsToFind}}, {"_id": 0, "preparation": 0}))
# print(list(mycollection.find({"ingredients": {"$all": ingredientsToFind}})))

# for i in mydoc:
#    print(i["name"], "-", i["ingredients"], end="\n", sep=" ")