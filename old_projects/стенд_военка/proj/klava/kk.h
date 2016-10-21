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
