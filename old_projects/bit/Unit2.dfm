object Form2: TForm2
  Left = 0
  Top = 0
  Caption = 'Form2'
  ClientHeight = 333
  ClientWidth = 519
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  Menu = MainMenu1
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object MainMenu1: TMainMenu
    Left = 472
    object File1: TMenuItem
      Caption = 'File'
      object Logout1: TMenuItem
        Caption = 'Log out'
        OnClick = Logout1Click
      end
      object Exit1: TMenuItem
        Caption = 'Exit'
        OnClick = Exit1Click
      end
    end
    object Users1: TMenuItem
      Caption = 'Users'
      object New1: TMenuItem
        Caption = 'New...'
        OnClick = New1Click
      end
    end
  end
end
