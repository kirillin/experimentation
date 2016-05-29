object Form1: TForm1
  Left = 0
  Top = 0
  Caption = '5. '#1064#1080#1092#1088#1086#1074#1082#1072' (20 '#1073#1072#1083#1083#1086#1074')'
  ClientHeight = 597
  ClientWidth = 879
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object Label1: TLabel
    Left = 256
    Top = 24
    Width = 223
    Height = 23
    Caption = '5. '#1064#1080#1092#1088#1086#1074#1082#1072' (20 '#1073#1072#1083#1083#1086#1074')'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -19
    Font.Name = 'Tahoma'
    Font.Style = []
    ParentFont = False
  end
  object Label2: TLabel
    Left = 24
    Top = 91
    Width = 57
    Height = 13
    Caption = #1042#1074#1077#1076#1080#1090#1077' N:'
  end
  object Label5: TLabel
    Left = 24
    Top = 110
    Width = 136
    Height = 13
    Caption = #1042#1099#1073#1077#1088#1080#1090#1077' '#1084#1077#1089#1090#1072' '#1087#1088#1086#1088#1077#1079#1077#1081':'
    Visible = False
  end
  object Label8: TLabel
    Left = 435
    Top = 88
    Width = 215
    Height = 13
    Caption = #1042#1099' '#1084#1086#1078#1077#1090#1077' '#1079#1072#1096#1080#1092#1088#1086#1074#1072#1090#1100' '#1089#1090#1088#1086#1082#1091' '#1076#1083#1080#1085#1085#1086#1081':'
  end
  object Label7: TLabel
    Left = 525
    Top = 318
    Width = 125
    Height = 13
    Caption = #1056#1072#1089#1096#1080#1092#1088#1086#1074#1072#1085#1085#1099#1081' '#1090#1077#1082#1089#1090':'
    Visible = False
  end
  object Label6: TLabel
    Left = 656
    Top = 88
    Width = 3
    Height = 13
  end
  object Label4: TLabel
    Left = 523
    Top = 202
    Width = 127
    Height = 13
    Caption = #1058#1077#1082#1089#1090' '#1076#1083#1103' '#1088#1072#1089#1096#1080#1092#1088#1086#1074#1082#1080':'
  end
  object Label3: TLabel
    Left = 529
    Top = 107
    Width = 121
    Height = 13
    Caption = #1058#1077#1082#1089#1090' '#1076#1083#1103' '#1079#1072#1096#1080#1092#1088#1086#1074#1082#1080':'
  end
  object StringGrid1: TStringGrid
    Left = 24
    Top = 131
    Width = 400
    Height = 400
    ColCount = 4
    FixedCols = 0
    FixedRows = 0
    TabOrder = 0
    Visible = False
    OnClick = StringGrid1Click
  end
  object Edit3: TEdit
    Left = 87
    Top = 88
    Width = 34
    Height = 21
    MaxLength = 2
    NumbersOnly = True
    TabOrder = 1
    OnChange = Edit3Change
  end
  object Memo1: TMemo
    Left = 656
    Top = 107
    Width = 185
    Height = 89
    Lines.Strings = (
      'Memo1')
    MaxLength = 100
    TabOrder = 2
  end
  object Button2: TButton
    Left = 552
    Top = 221
    Width = 98
    Height = 25
    Caption = #1056#1072#1089#1096#1080#1092#1088#1086#1074#1072#1090#1100
    TabOrder = 6
    OnClick = Button2Click
  end
  object Button1: TButton
    Left = 552
    Top = 129
    Width = 98
    Height = 25
    Caption = #1047#1072#1096#1080#1092#1088#1086#1074#1072#1090#1100
    TabOrder = 5
    OnClick = Button1Click
  end
  object Memo3: TMemo
    Left = 656
    Top = 315
    Width = 185
    Height = 89
    Lines.Strings = (
      'Memo1')
    MaxLength = 100
    ReadOnly = True
    TabOrder = 4
    Visible = False
  end
  object Memo2: TMemo
    Left = 656
    Top = 202
    Width = 185
    Height = 89
    Lines.Strings = (
      'Memo1')
    MaxLength = 100
    TabOrder = 3
  end
  object Button3: TButton
    Left = 184
    Top = 104
    Width = 75
    Height = 25
    Caption = #1054#1095#1080#1089#1090#1080#1090#1100
    TabOrder = 7
    Visible = False
    OnClick = Button3Click
  end
end
