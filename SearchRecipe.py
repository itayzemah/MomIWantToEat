import pymongo as myDB
import sys

myclient = myDB.MongoClient('localhost:27017')
mydb = myclient['mydb']
mycollection = mydb["recipe"]
first = sys.argv[1]

#search only by name.
if len(sys.argv) == 2:
    searchByIngre = mycollection.find_one({"name": first})
    # print(searchByIngre)
    print(searchByIngre['name'])
    print(searchByIngre['ingredients names'])
    print(searchByIngre['ingredients quantities'])
    print(searchByIngre['preparation'])
    sys.exit(0)


# search with name and ingredients.
if first == '~noTitle~':
    ingre = [sys.argv[i] for i in range(2, len(sys.argv))]
    searchByIngre = mycollection.find({"ingredients names": {"$all": ingre}})
    for cur in searchByIngre:
    # print(searchByIngre)
        print(cur['name'])
        print(cur['ingredients names'])
        print(cur['ingredients quantities'])
        print(cur['preparation'])

    # for rec in searchByIngre:
    #     l1 = []
    #     l2 = []
    #     print(rec['name'])
    #     lst1 = rec['ingredients names']
    #     for x in lst1:
    #         if x.strip() is not '' or not ' ':
    #             l1.append(x)
    #     print(l1)
    #     lst2 = rec['ingredients quantities']
    #     print(lst2)
    #     print(rec['preparation'])

if len(sys.argv) > 2 and first is not '~noTitle~':
    searchByName =mycollection.find({"name": first})
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
        print(lst2)
        print(rec['preparation'])
    sys.exit(0)



# x =mydb.mycollection.find({"ingredients": {'$regex': '/m/'}})
# print(x)
# print(list(mycollection.find()))
#    mydoc = list(mycollection.find({"ingredients": {"$all": ingredientsToFind}}, {"_id": 0, "preparation": 0}))
# print(list(mycollection.find({"ingredients": {"$all": ingredientsToFind}})))

# for i in mydoc:
#    print(i["name"], "-", i["ingredients"], end="\n", sep=" ")