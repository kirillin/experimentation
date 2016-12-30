object Form1: TForm1
  Left = 0
  Top = 0
  Caption = #1054#1082#1085#1086' '#1088#1077#1075#1080#1089#1090#1088#1072#1094#1080#1080
  ClientHeight = 201
  ClientWidth = 481
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  OldCreateOrder = False
  OnCreate = FormCreate
  OnShow = FormShow
  PixelsPerInch = 96
  TextHeight = 13
  object Label1: TLabel
    Left = 74
    Top = 62
    Width = 81
    Height = 13
    Caption = #1042#1074#1077#1076#1080#1090#1077' '#1092#1088#1072#1079#1091':'
  end
  object Label4: TLabel
    Left = 347
    Top = 132
    Width = 111
    Height = 13
    Alignment = taRightJustify
    Caption = #1042#1074#1077#1076#1080#1090#1077' '#1077#1075#1086' '#1077#1097#1077' '#1088#1072#1079':'
  end
  object Label2: TLabel
    Left = 64
    Top = 89
    Width = 91
    Height = 13
    Caption = #1042#1074#1077#1076#1080#1090#1077' '#1077#1097#1077' '#1088#1072#1079':'
  end
  object Label3: TLabel
    Left = 347
    Top = 113
    Width = 111
    Height = 13
    Alignment = taRightJustify
    Caption = #1042#1074#1077#1076#1080#1090#1077' '#1077#1075#1086' '#1077#1097#1077' '#1088#1072#1079':'
  end
  object Label5: TLabel
    Left = 57
    Top = 27
    Width = 98
    Height = 13
    Caption = #1055#1088#1080#1076#1091#1084#1072#1081#1090#1077' '#1083#1086#1075#1080#1085':'
  end
  object Edit1: TEdit
    Left = 161
    Top = 59
    Width = 297
    Height = 21
    TabOrder = 0
    Text = 'Edit1'
    OnClick = Edit1Click
    OnKeyPress = Edit1KeyPress
  end
  object Edit2: TEdit
    Left = 161
    Top = 86
    Width = 297
    Height = 21
    TabOrder = 1
    Text = 'Edit2'
    OnClick = Edit2Click
    OnKeyPress = Edit2KeyPress
  end
  object Button1: TButton
    Left = 312
    Top = 161
    Width = 146
    Height = 25
    Caption = #1057#1086#1079#1076#1072#1090#1100' '#1087#1086#1083#1100#1079#1086#1074#1072#1090#1077#1083#1103
    TabOrder = 2
    OnClick = Button1Click
  end
  object Edit3: TEdit
    Left = 161
    Top = 24
    Width = 160
    Height = 21
    TabOrder = 3
    Text = 'Edit3'
  end
  object Timer1: TTimer
    Interval = 10
    OnTimer = Timer1Timer
    Left = 8
    Top = 64
  end
end
