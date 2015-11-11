LOG_FILE_NAME = "Log.txt"

def hash_multiply(code, home_locations):
    return (ord(code[0]) * ord(code[1]) * ord(code[2])) % home_locations

def hash_add(code, home_locations):
    return (ord(code[0]) + ord(code[1]) + ord(code[2])) % home_locations

def hash_concatenate(code, home_locations):
    return (ord(code[0]) * 10000 + (ord(code[1]) * 100 + ord(code[2]))) % home_locations

def hash_concatenate_backward(code, home_locations):
    return (ord(code[2]) * 10000 + (ord(code[1]) * 100 + ord(code[0]))) % home_locations

def get_hash_function_number(hash_function):
    if hash_function == hash_multiply: return 1
    elif hash_function == hash_add: return 2
    elif hash_function == hash_concatenate: return 3
    elif hash_function == hash_concatenate_backward: return 4

def print_hash_table(hash_table, chaining):
    string = "LOC  CODE DRP%s\r\n" % (" LINK" if chaining else "")
    for i in range(0, len(hash_table)):
        if hash_table[i] != None:
            link = ""
            if chaining:
                link = "%03d" % hash_table[i][2] if hash_table[i][2] != None else -1
            string += "%03d> %s  %03d %s\r\n" % (
                        i,
                        hash_table[i][0],
                        hash_table[i][1],
                        link
                    )
        else:
            string += "%03d>\r\n" % (i)
    return string

def create_hash_table(filename, hash_function, home_locations, collision_locations, log_file):
    hash_table = [None for x in range(0, home_locations + collision_locations)]
    keys = []
    with open(filename, 'r') as raw_data:
        raw_data_file_identifier = filename[7:filename.find('.')]
        log_file.write("RAW DATA FILE:  %s\r\n" % raw_data_file_identifier)
        log_file.write("HASH FUNCTION:  %d  (with maxNHomeLoc: %d)\r\n" % (
                get_hash_function_number(hash_function), 
                home_locations
            ))
        log_file.write("COL RESOL ALG:  %d  %s\r\n" % (
                1 if collision_locations == 0 else 2,
                "(Linear, Embedded)" if collision_locations == 0 else "(Chaining, Separate)"
            ))
        log_file.write("N_HOME: %d,  N_COLL:  %d  --> %d\r\n" % (
                home_locations,
                collision_locations,
                home_locations + collision_locations
            ))

        for line in raw_data:
            # Extract data from the SQL query
            data = line[line.find('(') + 1:line.find(')')]
            columns = data.split(',')
            code = columns[0][1:-1]
            keys.append(code)
            data_record_pointer = int(columns[1])
            
            home_address = hash_function(code, home_locations)

            if hash_table[home_address] == None:
                # Insert the item
                hash_table[home_address] = [code, data_record_pointer, None]
            elif collision_locations == 0:
                # There are no collision locations, so embed the collisions in the home space.
                # Find an empty space (None)
                collision_index = home_address + 1
                try:
                    while hash_table[collision_index] != None and collision_index != home_address:
                        collision_index += 1
                        # Wrap around
                        if collision_index == home_locations: collision_index = 0
                except IndexError:
                    pass
                # Insert the item if there is room
                # (code, drp, link)
                if collision_index < home_locations: hash_table[collision_index] = [code, data_record_pointer, None]
                # TODO: Handle no room left
            else:
                # Add to the end of the hash table before home_locations + collision_locations.
                collision_index = home_locations
                while collision_index < len(hash_table) and hash_table[collision_index] != None:
                    collision_index += 1

                if collision_index < len(hash_table):
                    # Add the item to the collision space.
                    hash_table[collision_index] = [code, data_record_pointer, hash_table[home_address][2]]
                    # Find the previous "last" item in the collision space, for this synonym family.
                    hash_table[home_address][2] = collision_index

    # Average Search Path calculation
    comparisons = 0
    for key in keys:
        location = hash_function(key, home_locations)
        comparisons += 1
        while hash_table[location][0] != key:
            comparisons += 1
            # Is there a link field?
            if len(hash_table[location]) >= 2: 
                if hash_table[location][2] == None:
                    break
                else:
                    location = hash_table[location][2]
            else:
                # if not, that means that we are using linear and embedded.
                location += 1

    average_search_path = float(comparisons) / float(len(keys))

    log_file.write("AVE SEARCH PATH (for successful):  (%d+%d)/(%d+%d) --> %.1f\r\n" % (
            home_locations,
            len(keys),
            home_locations,
            collision_locations,
            round(average_search_path, 1)
        ))

    if raw_data_file_identifier == "All":
        log_file.write("HASH TABLE:  big table --> saved paper by not printing it\r\n")
    else:
        log_file.write("HASH TABLE:\r\n%s\r\n" % (
                print_hash_table(hash_table, collision_locations > 0)
            ))

    return hash_table

def case(number, raw_data_file, hash_function, home_locations, collision_locations, log_file):
    log_file.write("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\r\n")
    log_file.write("CASE: %d\r\n" % number)
    create_hash_table(raw_data_file, hash_function, home_locations, collision_locations, log_file)
    log_file.write("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\r\n")

TEST_FILENAME = "RawDataTest.csv"
ALL_FILENAME = "RawDataAll.csv"

with open("Log.txt", 'w') as log_file:
    case(1, TEST_FILENAME, hash_multiply, 20, 20, log_file)
    case(2, TEST_FILENAME, hash_add, 20, 20, log_file)
    case(3, TEST_FILENAME, hash_concatenate, 20, 20, log_file)
    case(4, TEST_FILENAME, hash_concatenate_backward, 20, 20, log_file)
    case(5, TEST_FILENAME, hash_multiply, 30, 30, log_file)
    case(6, TEST_FILENAME, hash_multiply, 30, 0, log_file)
    case(7, ALL_FILENAME, hash_multiply, 240, 240, log_file)
    case(8, ALL_FILENAME, hash_multiply, 260, 260, log_file)
    case(9, ALL_FILENAME, hash_multiply, 350, 350, log_file)
    case(10, ALL_FILENAME, hash_multiply, 240, 0, log_file)
    case(11, ALL_FILENAME, hash_multiply, 260, 0, log_file)
