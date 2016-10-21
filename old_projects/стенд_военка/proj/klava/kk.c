#include "kk.h"

#ifndef KK_H
#define KK_H



#define klav11 '1'
#define klav12 '4'
#define klav13 '7'
#define klav14 '*'
#define klav21 '2'
#define klav22 '5'
#define klav23 '8'
#define klav24 '0'
#define klav31 '3'
#define klav32 '6'
#define klav33 '9'
#define klav34 '#'
#define klav41 'A'
#define klav42 'B'
#define klav43 'C'
#define klav44 'D'

#define stolb1  PB0
#define stolb2  PB1
#define stolb3  PB2
#define stolb4  
#define stroka1 PB4
#define stroka2 PB5
#define stroka3 PB6
#define stroka4 PB7

#define DDR_stolb1  DDRB
#define DDR_stolb2  DDRB
#define DDR_stolb3  DDRB
#define DDR_stolb4
#define DDR_stroka1 DDRB
#define DDR_stroka2 DDRB
#define DDR_stroka3 DDRB
#define DDR_stroka4 DDRB

#define PIN_stolb1  PINB
#define PIN_stolb2  PINB
#define PIN_stolb3  PINB
#define PIN_stolb4  
#define PIN_stroka1 PINB
#define PIN_stroka2 PINB
#define PIN_stroka3 PINB
#define PIN_stroka4 PINB

#define PORT_stolb1  PORTB
#define PORT_stolb2  PORTB
#define PORT_stolb3  PORTB
#define PORT_stolb4
#define PORT_stroka1 PORTB
#define PORT_stroka2 PORTB
#define PORT_stroka3 PORTB
#define PORT_stroka4 PORTB

void init_klav (void);
void skan_klav (void);
extern unsigned char klav;

#endif 


void init_klav (void)
{
    DDR_stolb1  &= ~_BV(stolb1);
    DDR_stolb2  &= ~_BV(stolb2);
    DDR_stolb3  &= ~_BV(stolb3);
    DDR_stolb4  &= ~_BV(stolb4);//столбцы -  входы
    DDR_stroka1 |=  _BV(stroka1);
    DDR_stroka2 |=  _BV(stroka2);
    DDR_stroka3 |=  _BV(stroka3);
    //DDR_stroka4 |=  _BV(stroka4);//строки - выходы
	
	PORT_stolb1  |= _BV(stolb1);
    PORT_stolb2  |= _BV(stolb2);
    PORT_stolb3  |= _BV(stolb3);
    PORT_stolb4  |= _BV(stolb4);//столбцы - входы с подт€гивающими резисторами
    PORT_stroka1 |= _BV(stroka1);
    PORT_stroka2 |= _BV(stroka2);
    PORT_stroka3 |= _BV(stroka3);
    //PORT_stroka4 |= _BV(stroka4);//строки - выходы с лог.1
}

 void skan_klav (void)
{
    klav = 0;//если не нажата ни одна клавиша значение переменной обнул€ем
	PORT_stroka1 &= ~_BV(stroka1);_delay_us(100);//в 1-ю строку даЄм 0
	     if(bit_is_clear(PIN_stolb1, stolb1)) klav = klav11;//если он в 1-м столбце, то нажата клавиша 11
	else if(bit_is_clear(PIN_stolb2, stolb2)) klav = klav12;
	else if(bit_is_clear(PIN_stolb3, stolb3)) klav = klav13;
	else if(bit_is_clear(PIN_stolb4, stolb4)) klav = klav14;
	PORT_stroka1 |=  _BV(stroka1);
	
	PORT_stroka2 &= ~_BV(stroka2);_delay_us(100);
	     if(bit_is_clear(PIN_stolb1, stolb1)) klav = klav21;
	else if(bit_is_clear(PIN_stolb2, stolb2)) klav = klav22;
	else if(bit_is_clear(PIN_stolb3, stolb3)) klav = klav23;
	else if(bit_is_clear(PIN_stolb4, stolb4)) klav = klav24;
	PORT_stroka2 |=  _BV(stroka2);
	
	PORT_stroka3 &= ~_BV(stroka3);_delay_us(100);
	     if(bit_is_clear(PIN_stolb1, stolb1)) klav = klav31;
	else if(bit_is_clear(PIN_stolb2, stolb2)) klav = klav32;
	else if(bit_is_clear(PIN_stolb3, stolb3)) klav = klav33;
	else if(bit_is_clear(PIN_stolb4, stolb4)) klav = klav34;
	PORT_stroka3 |=  _BV(stroka3);
	
	/*PORT_stroka4 &= ~_BV(stroka4);_delay_us(100);
	     if(bit_is_clear(PIN_stolb1, stolb1)) klav = klav41;
	else if(bit_is_clear(PIN_stolb2, stolb2)) klav = klav42;
	else if(bit_is_clear(PIN_stolb3, stolb3)) klav = klav43;
	else if(bit_is_clear(PIN_stolb4, stolb4)) klav = klav44;
	PORT_stroka4 |=  _BV(stroka4);*/
}