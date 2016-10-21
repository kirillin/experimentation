#ifndef INDICATOR_H
#define INDICATOR_H

#include <ioavr.h>

#define PORT_IND PORTB
#define DDR_IND DDRB
#define PORT_K PORTD
#define DDR_K DDRD
#define KAT1 0
#define KAT2 1

void IND_Init(void);
void IND_Conv(unsigned char value);
void IND_Update(void);

#endif //INDICATOR_H