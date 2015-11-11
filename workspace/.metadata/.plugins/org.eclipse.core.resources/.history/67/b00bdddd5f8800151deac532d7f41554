'''
**************************************************************
Crowd Organizer App
Author: Caleb Viola
CrowdOrganizer.py
Main controller for application which manages a priority queue
for customers to be served at BetterBuy.
Reads file containing initialization data.
**************************************************************
'''
from CustomerPrQ import *
import os


class CrowdOrganizer:

    @staticmethod
    def main():
        prQ = CustomerPrQ('LineAt6Am.csv')
        log = open('Log.txt', 'w')
        log.write('>>> Program starting\n')

        with open('Events.txt', 'r') as file:
            for line in file:
                if not line.startswith('//', 0, 2):
                    raw = line.rstrip('\n')
                    func = {'O':(prQ.arrangeCustomerQ, log),
                            'C':(prQ.serveRemainingCustomers, log),
                            'N':(prQ.addCustomerToQ, (raw.split(',')[1:], True, log)),
                            'S':(prQ.serveACustomer, log)}[raw[:1]]
                    func[0](func[1])
        log.close()

os.remove('Log.txt') if os.path.exists('Log.txt') else None
CrowdOrganizer.main()
