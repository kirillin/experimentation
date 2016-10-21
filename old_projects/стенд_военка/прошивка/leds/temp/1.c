
#include <avr/io.h>
#include <util/delay.h>

// Строки
#define DS_PORT_row    PORTA
#define ST_CP_row_PORT PORTA
#define SH_CP_row_PORT PORTA
#define DS_PIN_row     0
#define ST_CP_PIN_row  1
#define SH_CP_PIN_row  2

//Столбцы
#define DS_PORT_col    PORTA
#define ST_CP_col_PORT PORTA
#define SH_CP_col_PORT PORTA
#define DS_PIN_col     3
#define ST_CP_PIN_col  4
#define SH_CP_PIN_col  5

 
#define DS_row_low()  DS_PORT_row &= ~_BV(DS_PIN_row)
#define DS_row_high() DS_PORT_row |= _BV(DS_PIN_row)
#define ST_CP_row_low()  ST_CP_row_PORT &= ~_BV(ST_CP_PIN_row)
#define ST_CP_row_high() ST_CP_row_PORT |= _BV(ST_CP_PIN_row)
#define SH_CP_row_low()  SH_CP_row_PORT &= ~_BV(SH_CP_PIN_row)
#define SH_CP_row_high() SH_CP_row_PORT |= _BV(SH_CP_PIN_row)
 
#define DS_col_low()  DS_PORT_col &= ~_BV(DS_PIN_col)
#define DS_col_high() DS_PORT_col |= _BV(DS_PIN_col)
#define ST_CP_col_low()  ST_CP_col_PORT &= ~_BV(ST_CP_PIN_col)
#define ST_CP_col_high() ST_CP_col_PORT |= _BV(ST_CP_PIN_col)
#define SH_CP_col_low()  SH_CP_col_PORT &= ~_BV(SH_CP_PIN_col)
#define SH_CP_col_high() SH_CP_col_PORT |= _BV(SH_CP_PIN_col)


//Define functions
//======================
void ioinit(void);
void output_led_state(unsigned char __led_state);
//======================
 
int main (void)
{
   ioinit(); //Setup IO pins and defaults
 
   while(1)
   {
      /*
        Output a knight rider pattern
       
        10000000
        01000000
        00100000
        00010000
        00001000
        00000100
        00000010
        00000001
        00000010
        00000100
        00001000
        00010000
        00100000
        01000000
      */
     
      for (int i=7;i>=0;i–)
      {
         output_led_state(_BV(i));
         _delay_ms(100);
      }
     
      for (int i=1;i<7;i++)
      {
         output_led_state(_BV(i));
         _delay_ms(100);
      }
   }
}
 
 
void ioinit (void)
{
    DDRC  = 0b00000111; //1 = output, 0 = input
    PORTC = 0b00000000;
}
 
void output_led_state(unsigned char __led_state)
{
   SH_CP_low();
   ST_CP_low();
   for (int i=0;i<8;i++)
   {
      if (bit_is_set(__led_state, i))
         DS_high();
      else  
         DS_low();
     
 
      SH_CP_high();
      SH_CP_low();
   }
   ST_CP_high();
}








****************************************

//Author HiSER (c)2010
//Chip type : ATmega8
//AVR Core Clock frequency: 1.000000 MHz
#include <mega8.h>
#include <delay.h>
#define SH_CP PORTB.1  
#define ST_CP PORTB.2  
#define DS PORTB.0  

unsigned char led_table[10]={0b00000011,0b10011111,0b00100101,0b00001101,0b10011001,0b01001001,0b01000001,0b00011111,0b00000001,0b00001001};
unsigned long show_data=12345678; //Отображаемая цифра
unsigned long dig_data=0;
unsigned char dig_number=0; //Отображаемый разряд

void show_dig() {
unsigned char data[2];
unsigned char i;
ST_CP=0;
if (dig_number==0) dig_data=show_data; //Если показываем первую цифру обнавляем переменную
data[0]=dig_data%10; //Получаем разряд
dig_data/=10;
data[0]=led_table[data[0]]; //Получаем данные для сигментов
data[1]=1<<dig_number;
for (i=0;i<8;i++) {
DS=data[1]&1;
SH_CP=1;
SH_CP=0;
data[1]>>=1;
}
for (i=0;i<8;i++) {
DS=data[0]&1;
SH_CP=1;
SH_CP=0;
data[0]>>=1;
}
dig_number++;
if (dig_number>7) dig_number=0;
ST_CP=1;
}

void main(void) {
DDRB=7;
PORTB=0;
while (1) show_dig(); //Отображаем разряды
}  


