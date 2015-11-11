'''
*******************************************************
Crowd Organizer App
Author: Caleb Viola
CustomerPrQ.py
Implementation of min-heap for managing priority queue.
*******************************************************
'''
import math


class CustomerPrQ():
    #************************
    def __init__(self, name):
        self.initialPriorityValue = 101
        self.heapNodes = []  # Heap storage
        self.fileName = name

    #*******************************
    def arrangeCustomerQ(self, log):
        log.write('STORE IS OPENING\n')
        with open(self.fileName, 'r') as myfile:
            for line in myfile:
                if not line.startswith('//', 0, 2):
                    self.addCustomerToQ(line.rstrip('\n').split(','), log)
        log.write('>>> Initial heap built containing {} nodes\n\n'.format(len(self.heapNodes)))

    #**************************************
    def serveRemainingCustomers(self, log):
        log.write('\n')
        log.write('STORE IS CLOSING\n')
        log.write('>>> Heap currently has {} nodes remaining\n'.format(len(self.heapNodes)))
        while self.heapNodes:
            self.serveACustomer(log)
        log.write('>>> Heap is now empty\n')
        log.write('>>> Program terminating\n')

    '''
    :args contains = (raw string, toPrint boolean, log object)
    '''
    #*******************************************
    def addCustomerToQ(self, *args):
        raw, toPrint, log = args if (len(args) > 1) else args[0]
        nextInLine = self.__determinePriorityValue(raw[1:])
        if toPrint:
            log.write('ADDING: {} ({})\n'.format(''.join(raw[:1]), nextInLine))
        self.__heapInsert(''.join(raw[:1]), nextInLine)
        self.initialPriorityValue += 1

    #*****************************
    def serveACustomer(self, log):
        if self.heapNodes:
            log.write('SERVING: {} ({})\n'.format(self.heapNodes[0].name,
                                                  self.heapNodes[0].priorityValue))
            self.__heapDelete()
        else:
            log.write('>>> Heap is empty\n')

    #*******************************************
    def __heapInsert(self, name, priorityValue):
        x = Node(name, priorityValue)
        self.heapNodes.append(x)
        self.walkUp(len(self.heapNodes) - 1)

    #**********************
    def __heapDelete(self):
        minItem = self.heapNodes[0]
        self.heapNodes[0] = self.heapNodes[len(self.heapNodes) - 1]
        self.heapNodes.pop()
        self.walkDown(0)
        return minItem

    #***************************************
    def __determinePriorityValue(self, raw):
        subtract = 0
        for jumpTheQPoints in raw:
            if not jumpTheQPoints == '':
                subtract += {jumpTheQPoints: (15 if jumpTheQPoints.isnumeric() and (int(jumpTheQPoints) >= 65 or int(jumpTheQPoints) >= 80) else 0),
                             'employee': 25,
                             'owner': 80,
                             'vip': 5,
                             'superVIP': 10}[jumpTheQPoints]
        return self.initialPriorityValue - subtract


    #***************************
    def walkUp(self, startFrom):
        i = startFrom
        while (i > 0) and \
                (self.heapNodes[i].priorityValue < self.heapNodes[math.trunc((i - 1) / 2)].priorityValue):
            self.heapNodes[i], self.heapNodes[math.trunc((i - 1) / 2)] = \
                self.heapNodes[math.trunc((i - 1) / 2)], self.heapNodes[i]  # Swap
            i = math.trunc((i - 1) / 2)

    #*****************************
    def walkDown(self, startFrom):
        i = startFrom
        smCh = self.subOfSmCh(i)
        while (2 * i + 1) <= len(self.heapNodes) - 1 and \
                (self.heapNodes[i].priorityValue > self.heapNodes[smCh].priorityValue):
            self.heapNodes[i], self.heapNodes[smCh] = \
                self.heapNodes[smCh], self.heapNodes[i]  # Swap
            i = smCh
            smCh = self.subOfSmCh(i)

    #**********************
    def subOfSmCh(self, i):
        if ((2*i + 2) > len(self.heapNodes) - 1 or
                (self.heapNodes[2*i + 1].priorityValue <= self.heapNodes[2*i + 2].priorityValue)):
            return 2*i + 1
        else:
            return 2*i + 2


class Node:
    #***************************************
    def __init__(self, name, priorityValue):
        self.name = name
        self.priorityValue = priorityValue
