#!/usr/bin/python
import pprint


class City(object):
    def __init__(self, city_name, level):
        self.united = True
        self.city_name = str(city_name)
        self.level = level
        self.merged_roots_level = [level]
        self.root = None
        self.id = int(city_name)

    def __str__(self):
        return self.city_name

    def __repr__(self):
        return "name: " + str(self.city_name) + " level: " + str(self.level) + " united: " + str(
            self.united) + " root : " + str(self.root)


class Byte(object):
    def __init__(self, city_count, route_string):
        cities = []
        for i in range(city_count):
            level = None
            if i == 0:
                level = 0
            cities.append(City(i, level))
        set_city_level(cities, route_string)
        self.cities = cities

    """ 
    Main function which calculate counter
    """     
    def execute(self):
        p = pprint.PrettyPrinter(indent=4)
        counter = 0
        p.pprint(self.cities)
        while True:
            while True:
                if is_exist_mergeable_city(self.cities):
                    city = find_max_level(self.cities)
                    self.merge(city)
                else:
                    break
            p.pprint(self.cities)
            counter += 1
            self.set_all_cities_unvisited()
            if not is_exist_mergeable_city(self.cities):
                break
        return counter

    """
    Set True all visited nodes
    """
    def set_all_cities_unvisited(self):
        for city in self.cities:
            city.united = True


    """
    Merge possible cities
    """
    def merge(self, city):
        if city.united and city.root.united:
            root = city.root
            root.united = False
            print root.city_name + "====>" + city.city_name
            root.city_name += "," + city.city_name
            root.merged_roots_level += city.merged_roots_level
            self.move_city_roots(root, city)
            self.cities.remove(city)
            self.set_root_branches_visited(root)
        else:
            raise "Merge Error"

    """
    Set branches which has the same root
    """
    def set_root_branches_visited(self, root):
        temp_cities = filter(lambda c: not c.root == None and c.root.id == root.id, self.cities)
        for c in temp_cities:
            c.united = False


    """
    Copy roots of city before remove
    """
    def move_city_roots(self, root, city):
        temp_cities = filter(lambda c: not c.root == None and c.root.id == city.id, self.cities)
        for c in temp_cities:
            c.root = root

"""
Find Max Level
"""
def find_max_level(cities):
    temp_cities = filter(lambda c: c.united, cities)
    return max(temp_cities, key=lambda x: x.level)

"""
Checks is there shill mergeable cities
"""
def is_exist_mergeable_city(cities):
    filtered_cities = filter(lambda c: c.united, cities)
    if len(filtered_cities) > 1:
        return True
    return False

"""
Sets levels of cities while initializing
"""
def set_city_level(cities, route_string):
    routes = route_string.split(" ")
    queue = [cities[0]]
    visited = []
    while queue:
        city = queue.pop(0)
        visited.append(city)
        i = 1
        for route in routes:
            if city.city_name == route:
                target_city = cities[i]
                if not visited.__contains__(target_city):
                    queue.append(target_city)
                target_city.level = city.level + 1
                target_city.root = city
            i += 1


def main():
    # case 1


    route_string = "0 1 2"
    city_count = 4
    # case 2
    route_string = "0 1 2 0 0 3 3"
    city_count = 8

    # case 3
    # route_string = "0 1 1 1 1 0 2 2"
    # city_count = 9
    route_string = "3 4 0 0 1 5 2"
    city_count = 8

    city_count = int(raw_input('Enter city count : '))
    route_string = raw_input('Enter city relations : ')

    if len(route_string.split(" "))  + 1 == city_count:
            byte = Byte(city_count, route_string)
            print byte.execute()
    else:
        print "Input Error"

    

if __name__ == "__main__":
    main()
