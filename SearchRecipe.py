import pymongo as myDB
import sys

myclient = myDB.MongoClient('localhost:27017')
mydb = myclient['mydb']
mycollection = mydb["recipe"]
name = sys.argv[1]

if len(sys.argv) > 2:
    ingre = sys.argv[2]
    ingreArr = ingre.split(",")
    searchByIngre = mycollection.find({"ingredients": {"$all": ingreArr}})
    for rec in searchByIngre:
        l1 = []
        l2 = []
        print(rec['name'])
        lst1 = rec['ingredients names']
        for x in lst1:
            if x.strip() is not '' or not ' ':
                l1.append(x)
        print(l1)
        lst2 = rec['ingredients quantities']
        for x in lst2:
            if x.strip() is not '' or not ' ':
                l2.append(x)
        print(l2)
        print(rec['preparation'])

if name is not '~noTitle~':
    searchByName =mycollection.find({"name": name})
    # print(mycollection.find({"name": name}))
    for rec in searchByName:
        l1 = []
        l2 = []
        print(rec['name'])
        lst1 = rec['ingredients names']
        for x in lst1:
            if x.strip() is not '' or not ' ':
                l1.append(x)
        print(l1)
        lst2 = rec['ingredients quantities']
        for x in lst2:
            if x.strip() is not '' or not ' ':
                l2.append(x)
        print(l2)
        print(rec['preparation'])
sys.exit(0)



# x =mydb.mycollection.find({"ingredients": {'$regex': '/m/'}})
# print(x)
# print(list(mycollection.find()))
#    mydoc = list(mycollection.find({"ingredients": {"$all": ingredientsToFind}}, {"_id": 0, "preparation": 0}))
# print(list(mycollection.find({"ingredients": {"$all": ingredientsToFind}})))

# for i in mydoc:
#    print(i["name"], "-", i["ingredients"], end="\n", sep=" ")