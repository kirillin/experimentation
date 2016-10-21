#ifndef LCD_H
#define LCD_H

#include <avr/io.h>            
#include <util/delay.h>
#include <avr/pgmspace.h>  

#define PORT_lcd PORTD
#define DDR_lcd  DDRD
#define PORT_RS  PORTD
#define DDR_RS   DDRD
#define PORT_EN  PORTD
#define DDR_EN   DDRD
#define RS       PD5          
#define EN       PD4  

void lcd_init(void); 
void lcd_com(unsigned char p);
void lcd_dat(unsigned char p);
void show_string_flash (unsigned char adress, const unsigned char *string);
void show_string_rom (unsigned char adress, const unsigned char *string);

#endif



