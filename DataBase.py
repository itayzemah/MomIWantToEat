import pymongo as myDB
import sys
import pprint

myclient = myDB.MongoClient('localhost:27017')
mydb = myclient['mydb']
mycollection = mydb["recipe"]


def find_name(name :str):
    return mycollection.find({"name": name}).distinct('name')


def find_ingredients(name):
    l = []
    lst = mycollection.find({"name": name}).distinct('ingredients names')
    for i in lst:
        if i.strip() is not '' or not ' ':
            l.append(i.strip())
    return l


def find_quantities(name):
    lst = mycollection.find({"name": name}).distinct('ingredients quantities')
    print(lst)
    l = []
    for i in lst:
        if i is not '' or not ' ':
            l.append(i)
    return l


def find_preperation(name):
    return mycollection.find({"name": name}).distinct('preparation')


def get_full_recipe(name):
    lst1 = find_name(name)
    lst2 = find_ingredients(name)
    lst4 = [str(x) for x in find_quantities(name)]
    lst3 = find_preperation(name)
    return "{}\n{}\n{}\n{}.".format(' '.join(lst1), ', '.join(lst2), ', '.join(lst4), ' '.join(lst3))


def print_rec(name):
    res = mycollection.find({"name": name}).next()
    # print(res.next())
    print(res['name'])
    print(res['ingredients names'])
    print(res['ingredients quantities'])
    print(res['preparation'])
    # print(res.("ingredients quantities"))


# def insert_reipe(name:str, ingredients:list, pre:str):
#     mydict = {"name": name, "ingredients": ingredients, "preparation" : pre}
#     mycollection.insert_one(mydict)



print_rec(sys.argv[1])
# print(get_full_recipe(sys.argv[1]))
sys.exit(0)
# x =mydb.mycollection.find({"ingredients": {'$regex': '/m/'}})
# print(x)
# print(list(mycollection.find()))
#    mydoc = list(mycollection.find({"ingredients": {"$all": ingredientsToFind}}, {"_id": 0, "preparation": 0}))
# print(list(mycollection.find({"ingredients": {"$all": ingredientsToFind}})))

# for i in mydoc:
#    print(i["name"], "-", i["ingredients"], end="\n", sep=" ")