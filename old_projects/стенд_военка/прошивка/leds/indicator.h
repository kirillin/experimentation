#ifndef INDICATOR_H
#define INDICATOR_H
#include <ioavr.h>

//Индикаторы

#define PORT_IND PORTB
#define DDR_IND DDRB
#define PORT_K PORTD
#define DDR_K DDRD
#define KAT1 0
#define KAT2 1
#define KAT3 2

#define PORT_PLUS PORTA
#define DDR_PLUS DDRA
#define PORT_BASE PORTD
#define DDR_BASE DDRD
#define BASE1 3
#define BASE2 4
#define BASE3 5
#define BASE4 6
#define BASE5 7

//Светодиодная матрица

// Строки
#define DS_PORT_row    PORTA
#define ST_CP_row_PORT PORTA
#define SH_CP_row_PORT PORTA
#define DS_PIN_row     0
#define ST_CP_PIN_row  1
#define SH_CP_PIN_row  2

#define DS_row_low()  DS_PORT_row &= ~_BV(DS_PIN_row)
#define DS_row_high() DS_PORT_row |= _BV(DS_PIN_row)
#define ST_CP_row_low()  ST_CP_row_PORT &= ~_BV(ST_CP_PIN_row)
#define ST_CP_row_high() ST_CP_row_PORT |= _BV(ST_CP_PIN_row)
#define SH_CP_row_low()  SH_CP_row_PORT &= ~_BV(SH_CP_PIN_row)
#define SH_CP_row_high() SH_CP_row_PORT |= _BV(SH_CP_PIN_row)

//Столбцы
#define DS_PORT_col    PORTA
#define ST_CP_col_PORT PORTA
#define SH_CP_col_PORT PORTA
#define DS_PIN_col     3
#define ST_CP_PIN_col  4
#define SH_CP_PIN_col  5
 
#define DS_col_low()           DS_PORT_col &= ~_BV(DS_PIN_col)
#define DS_col_high()          DS_PORT_col |= _BV(DS_PIN_col)
#define ST_CP_col_low()        ST_CP_col_PORT &= ~_BV(ST_CP_PIN_col)
#define ST_CP_col_high()       ST_CP_col_PORT |= _BV(ST_CP_PIN_col)
#define SH_CP_col_low()        SH_CP_col_PORT &= ~_BV(SH_CP_PIN_col)
#define SH_CP_col_high()       SH_CP_col_PORT |= _BV(SH_CP_PIN_col)


#define _BV(bit)              (1 << (bit))
#define bit_is_set(value, n) ((value) & _BV(n)) 

void IND_Init(void);
void ioinit(void);
void IND_Conv(unsigned int value);
void IND_Update(void);

#endif //INDICATOR_H